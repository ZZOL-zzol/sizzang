import React from "react";
import { useNavigate } from "react-router-dom";

const Button = (props) => {

  const navigate = useNavigate();

  return (
    // <div className="flex justify-center w-full my-5" onClick={props.type === 'mypage' && props.innerText === '소비관리'? ()=>navigate(' '): '' }>
      <div className="flex justify-center w-full my-5">
      <div
        className={`flex w-full h-fit items-center justify-center rounded-lg ${props.color}`}
        onClick={props.onClick}
      >
        <div className={props.type ==='main' || props.type ==='mypage'? "flex mx-10 w-full items-center text-xl font-bold justify-between h-20": "flex items-center text-xl font-bold"}>
          {props.innerText}
          {props.image ? (
            <div className="flex w-[40px] justify-center">
              <img
                src={props.image}
                alt="회사로고"
                className="w-[40px]"
              ></img>
            </div>
          ) : null}
        </div>
      </div>
    </div>
  );
};

export default Button;
