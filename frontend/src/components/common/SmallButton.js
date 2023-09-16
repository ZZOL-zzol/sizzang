import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

const SmallButton = (props) => {
  const location = useLocation();
  const [currentLocation, setCurrentLocation] = useState("main");

  useEffect(() => {
    const tmplocation = location.pathname.split("/");
    setCurrentLocation(tmplocation[1]);
  }, []);

  console.log(currentLocation);
  return (
    <button
      className={`btn btn-xs w-fit ${props.color} rounded-full`}
      onClick={
        props.innerText === "담기"
          ? () => {
              props.onClick();
            }
          : props.onReviewButtonClick
          ? () => props.onReviewButtonClick()
          : null
      }
    >
      {props.innerText}
    </button>
  );
};

export default SmallButton;
