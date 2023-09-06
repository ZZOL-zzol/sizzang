import React from "react";

const TextButton = (props) => {
  return (
    <div className="flex justify-center h-14 w-full ">
      <div
        className={`flex w-full items-center justify-center ${props.color}`}
        onClick={props.callback}
      >
        <div className="flex items-center gap-x-8">
          <div className="flex w-[35px] justify-center">
            <img src="./chacha2.jpg" alt="회사로고" className="w-[35px]"></img>
          </div>
          카카오로 로그인
        </div>
      </div>
    </div>
  );
};

export default TextButton;
