import { useDispatch, useSelector } from "react-redux";
import { setStatus } from "../../../slice/testListSlice";

const FilterTab = ({ handleNavigate }) => {
  const active = useSelector((state) => state.tests.status);
  const dispatch = useDispatch()

  const handleStatus = (value) => {
    dispatch(setStatus(value));
    handleNavigate("status", value);
  }


  const tabs = [
    { key: "all", label: "Tất cả" },
    { key: "notdone", label: "Chưa làm" },
    { key: "done", label: "Đã hoàn thành" },
  ];

  return (
    <div className="filter-tabs mt-3">
      <div className="flex flex-wrap gap-2">
        {tabs.map((tab) => (
          <div
            key={tab.key}
            onClick={() => handleStatus(tab.key)}
            className={`filter-tab text-sm font-medium cursor-pointer ${active === tab.key
              ? "active text-gray-900"
              : "text-gray-600"
              }`}
          >
            {tab.label}
          </div>
        ))}
      </div>
    </div>
  );
}
export default FilterTab