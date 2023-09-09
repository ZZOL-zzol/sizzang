import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";


const SmallButton = (props) => {
  const location = useLocation();
  const navigate = useNavigate();
  const [currentLocation, setCurrentLocation] = useState("main");

  useEffect(() => {
    const tmplocation = location.pathname.split("/");
    setCurrentLocation(tmplocation[1]);
  }, []);

  const letsgo = (value) => {
    console.log("실행");
      navigate("/"+value);

  };

  console.log(currentLocation);
  return (
    <button
      className={`btn btn-xs w-fit ${props.color} rounded-full`}
      onClick={
        props.innerText === "담기" ? () => {props.onClick();} : null
      }
    >
      {props.innerText}
    </button>
  );
};

export default SmallButton;
