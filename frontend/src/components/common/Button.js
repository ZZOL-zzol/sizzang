import React from "react";

const Button = (props) => {
  return (
    <div className="flex justify-center h-14 w-full ">
      <div
        className={`flex w-full items-center justify-center rounded-lg ${props.color}`}
        onClick={props.callback}
      >
        <div className="flex items-center">
          {props.innerText}
          {props.image ? (
            <div className="flex w-[35px] justify-center">
              <img
                src={props.image}
                alt="회사로고"
                className="w-[35px]"
              ></img>
            </div>
          ) : null}
        </div>
      </div>
    </div>
  );
};

export default Button;
