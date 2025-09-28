import { useState } from "react";
import {
  FaListAlt,
  FaGraduationCap,
  FaBriefcase,
  FaUniversity,
  FaCertificate,
  FaLanguage,
  FaGlobe,
  FaBook,
  FaChalkboardTeacher,
  FaLaptopCode,
  FaUserGraduate,
} from "react-icons/fa";
import { useDispatch, useSelector } from "react-redux";
import { setCategory } from "../../../slice/testListSlice";

export default function ExamCate({ handleNavigate }) {
  const dispatch = useDispatch();
  const active = useSelector((state) => state.tests.category);

  const handleCateChange = (event) => {
    // console.log("Button value:", event);
    dispatch(setCategory(event));
    handleNavigate("category", event);
  }


  const categories = [
    { key: "all", label: "Tất cả (456)", icon: <FaListAlt className="mr-2 " /> },
    { key: "ielts", label: "IELTS (128)", icon: <FaGraduationCap className="mr-2 text-blue-600 " /> },
    { key: "toeic", label: "TOEIC (85)", icon: <FaBriefcase className="mr-2 text-orange-600 " /> },
    { key: "toefl", label: "TOEFL (67)", icon: <FaUniversity className="mr-2 text-green-600 " /> },
    { key: "sat", label: "SAT (28)", icon: <FaCertificate className="mr-2 text-purple-600 " /> },
    { key: "vstep", label: "VSTEP (24)", icon: <FaLanguage className="mr-2 text-pink-600 " /> },
    { key: "gmat", label: "GMAT (42)", icon: <FaGlobe className="mr-2 text-indigo-600 " /> },
    { key: "gre", label: "GRE (53)", icon: <FaBook className="mr-2 text-red-600 " /> },
    { key: "pte", label: "PTE (31)", icon: <FaChalkboardTeacher className="mr-2 text-yellow-600 " /> },
    { key: "moet", label: "MOET (15)", icon: <FaLaptopCode className="mr-2 text-teal-600 " /> },
    { key: "cambridge", label: "Cambridge (22)", icon: <FaUserGraduate className="mr-2 text-cyan-600 " /> },
  ];

  return (
    <div className="mb-8">
      <div className="flex flex-wrap gap-3">
        {categories.map((cat) => (
          <button
            key={cat.key}
            onClick={() => handleCateChange(cat.key)}
            className={`flex items-center px-4 py-2 rounded-full font-medium transition cursor-pointer
              ${active === cat.key
                ? "bg-blue-600 !text-[#ffffff] shadow"
                : "bg-[#ffffff] text-gray-700 border hover:bg-gray-50"
              }`}
          >
            {cat.icon}
            {cat.label}
          </button>
        ))}
      </div>
    </div>
  );
}
