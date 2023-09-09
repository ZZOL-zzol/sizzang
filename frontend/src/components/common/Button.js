import React from "react";

const Button = (props) => {
  return (
    <div className="flex justify-center h-14 w-full ">
      <div
        className={`flex w-full h-fit items-center justify-center rounded-lg ${props.color}`}
        onClick={props.onClick}
      >
        <div className={props.type ==='main'? "flex mx-14 w-full items-center text-xl font-bold justify-between h-20": "flex items-center text-xl font-bold"}>
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
