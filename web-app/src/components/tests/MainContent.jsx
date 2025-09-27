import { Button, Form } from "antd";
import React, { useRef, useState } from "react";
import SideProgress from "./SideProgress";
import QuestionCard from "./QuestionCard";
import { questionOfTest } from "../../data/MockData";
import _ from "lodash";
import { useSelector } from "react-redux";

const MainContent = ({ editMode, testTitle, testId }) => {
    const [activeQuestion, setActiveQuestion] = useState(null);

    const [listQuestionNumber, setListQuestionNumber] = useState(0);
    const questionRefs = useRef({});
    const { userAnswers, questions } = useSelector((state) => state.questions);
    const numOfQues = 100;
    const parts = _.uniq(questions.map((item) => item.part));
    const questionsPerPart = _.countBy(questions, "part");
    const groupQuestion = _.groupBy(
        questions.map((q, i) => ({
            ...q,
            resourceContent: q.resourceContent ?? `null-${i}`
        })),
        "resourceContent"
    );
    // console.log("Group questions:", groupQuestion)


    const questionCardComponents = Object.entries(groupQuestion).map(
        ([key, item], groupIdx) => (

            <div key={key} className="mb-8">

                <QuestionCard
                    questions={item}
                    groupKey={key}
                    questionRefs={questionRefs}
                    resourceContent={item[0].resourceContent}
                    editMode={editMode}
                />
            </div>
        )
    );

    const movePage = (action) => {
        setListQuestionNumber((prev) => {
            let newIndex = prev;
            if (action === "next") {
                newIndex = Math.min(prev + 1, questionCardComponents.length - 1);
            }
            if (action === "previous") {
                newIndex = Math.max(prev - 1, 0);
            }

            const groupKey = Object.keys(groupQuestion)[newIndex];
            const groupQuestions = groupQuestion[groupKey];
            if (groupQuestions && groupQuestions.length > 0) {
                setActiveQuestion(groupQuestions[0].questionNumber);
                setTimeout(() => {
                    questionRefs.current[groupQuestions[0].questionNumber]?.scrollIntoView({
                        behavior: "smooth",
                        block: "center",
                    });
                }, 100);
            }

            return newIndex;
        });
    };


    const questionToGroupIndex = {};
    Object.entries(groupQuestion).forEach(([_, item], groupIdx) => {

        item.forEach((q) => {
            questionToGroupIndex[q.questionNumber] = groupIdx;
        });
    });
    console.log("User answers:", userAnswers);
    // console.log("Question Of test:", questions);
    return (
        <main className="flex min-h-screen">
            <div className="flex-1 p-6 overflow-y-auto">
                <div className="bg-white rounded-xl p-6 shadow-lg mb-8">
                    {questionCardComponents[listQuestionNumber]}

                    <div className={`${editMode ? "mt-16" : "mt-6"} flex justify-between`}>
                        <Button
                            type="primary"
                            className="rounded-lg !bg-black !w-32 !h-12 hover:!bg-gray-700 mx-4 !text-white"
                            onClick={() => movePage("previous")}
                            disabled={listQuestionNumber === 0}
                        >
                            Previous Part
                        </Button>
                        <Button
                            type="primary"
                            className="rounded-lg !w-32 !h-12"
                            onClick={() => movePage("next")}
                            disabled={listQuestionNumber === questionCardComponents.length - 1}
                        >
                            Next Part
                        </Button>
                    </div>
                </div>
            </div>
            <SideProgress
                parts={parts}
                questionsPerPart={questionsPerPart}
                currentIndex={listQuestionNumber}
                setCurrentIndex={setListQuestionNumber}
                questionToGroupIndex={questionToGroupIndex}
                questionRefs={questionRefs}
                activeQuestion={activeQuestion}
                setActiveQuestion={setActiveQuestion}
            />

        </main>
    );
};

export default MainContent;
