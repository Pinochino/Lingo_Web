import { Button, Card, Col, Row, Tabs } from "antd";
import { CheckOutlined, CloseOutlined, UnorderedListOutlined } from '@ant-design/icons';
import { useState, useMemo } from "react";
import DetailAnswer from "./DetailAnswer";
import { PartLengthToeic, PartLengthIELTSListening, PartLengthIELTSReading } from "../../../data/FixedData";
import { useSelector } from "react-redux";

function getSelectedParts(attempt) {
  const examType = attempt?.type;

  if (examType === "TOEIC") {
    return PartLengthToeic;
  }

  if (examType === "IELTS") {
    const sectionType = attempt?.sectionResults?.[0]?.type; // chỉ có 1 section
    if (sectionType === "Listening") return PartLengthIELTSListening;
    if (sectionType === "Reading") return PartLengthIELTSReading;
    return []; // fallback nếu thiếu dữ liệu
  }

  return [];
}

const SectionAnswer = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [Choice, setChoice] = useState("");
  const [Correct, setCorrect] = useState("");
  const [questionId, setQuestionId] = useState(null);

  const { attempt } = useSelector(state => state.attempts);

  const parts = useMemo(() => getSelectedParts(attempt), [attempt]);

  const Questions = ({ questionNumber, questionId, choice = "N", correct = "N" }) => {
    const check = choice === correct;
    return (
      <Col xs={{ flex: '100%' }} sm={{ flex: '50%' }} lg={{ flex: '25%' }}>
        <div className="flex flex-nowrap items-center justify-center space-x-2 rounded-lg px-3 py-2">
          <span className="text-black rounded-full flex items-center justify-center text-sm font-bold">
            {questionNumber}.
          </span>
          <span className="text-sm text-gray-700 whitespace-nowrap">{choice + " -> " + correct}</span>
          {check ? (
            <CheckOutlined className="!font-bold !text-emerald-400" />
          ) : (
            <CloseOutlined className="!font-bold !text-rose-400" />
          )}
          <Button
            size="small"
            type="text"
            className="!px-0 !text-blue-700"
            onClick={() => {
              setIsModalOpen(true);
              setChoice(choice);
              setCorrect(correct);
              setQuestionId(questionId);
            }}
          >
            [Chi tiết]
          </Button>
        </div>
      </Col>
    );
  };

  const items = parts.map((part) => ({
    key: String(part.part),
    label: `Part ${part.part}`,
    children: (
      <Row gutter={[16, 16]}>
        {Array.from({ length: part.length }).map((_, index) => {
          const globalIndex = part.start - 1 + index;

          if (!attempt?.answers || globalIndex >= attempt.answers.length) return null;

          const answerData = attempt.answers[globalIndex];

          return (
            <Questions
              key={globalIndex}
              questionNumber={globalIndex + 1}
              questionId={answerData?.questionId}
              choice={answerData?.userAnswer}
              correct={answerData?.correctAnswer}
            />
          );
        })}
      </Row>
    )
  }));

  return (
    <Card className="!shadow-lg !pb-3 !mt-7" id="my-section">
      <h3 className="text-xl font-bold text-gray-800 mb-6">
        <UnorderedListOutlined className="mr-2" />
        Chi tiết từng phần
      </h3>

      <Tabs
        defaultActiveKey={items[0]?.key ?? "1"}
        type="card"
        tabBarGutter={12}
        tabBarStyle={{ marginBottom: 24 }}
        items={items}
      />

      <DetailAnswer
        isModalOpen={isModalOpen}
        handleOk={() => setIsModalOpen(false)}
        handleCancel={() => setIsModalOpen(false)}
        correctAnswer={Correct}
        userAnswer={Choice}
        questionId={questionId}
      />
    </Card>
  );
};

export default SectionAnswer;
