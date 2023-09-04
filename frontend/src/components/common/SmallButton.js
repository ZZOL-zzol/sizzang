import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const color = "bg-secondary-container";

const SmallButton = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [currentLocation, setCurrentLocation] = useState("main");

  useEffect(() => {
    const tmplocation = location.pathname.split("/");
    setCurrentLocation(tmplocation[1]);
  }, []);

  const letsgo = (value) => {
    console.log("실행");
    if (value === "product") {
      navigate("/product");
    }
  };

  console.log(currentLocation);
  return (
    <button
      className={`btn btn-xs w-fit ${color}`}
      onClick={
        currentLocation === "main" ? () => letsgo("product") : null
      }
    >
      {currentLocation === "main" ? "상품찾기페이지 ㄱㄱ" : "작은버튼"}
    </button>
  );
};

export default SmallButton;
