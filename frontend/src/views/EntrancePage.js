import React from "react";
import { Link, useNavigate } from "react-router-dom";
import Button from "../components/common/Button";
import TextInput from "../components/common/TextInput";
import axios from "axios";

const LOGIN_URL = "http://localhost:8080/oauth/login/kakao";

const EntrancePage = () => {
  const onLoginClick = () => {
    // axios
    //   .post(
    //     `${LOGIN_URL}`,
    //     JSON.stringify({ userId: "userId", userPassword: "userPassword" }),
    //     {
    //       headers: { "Content-Type": "application/json" },
    //     }
    //   )
    //   .then((res) => {
    //     if (res.data) {
    //     }
    //   });
  };

  return (
    <div className=" w-full h-full bg-white outline outline-1">
      <div className="flex w-full h-3/5 items-center justify-center">
        <div>
          <img src="./ZZang_logo.svg" alt="logo" />
        </div>
      </div>
      <div className="flex flex-col h-2/5 justify-evenly  items-center">
        <div className="flex flex-col gap-2 w-full px-9">
          <TextInput placeholder="ID" />
          <TextInput placeholder="PASSWORD" />
        </div>
        <div className="flex flex-col gap-2 w-full items-center px-9">
          <Link to={"/main"} className="w-full">
            <Button
              innerText="로그인"
              color="bg-blue-200"
              onClick={() => onLoginClick}
            />
          </Link>
          <Link to={"/signup"} className="w-full">
            <Button innerText="회원가입" color="bg-blue-500" />
          </Link>
        </div>
      </div>
    </div>
  );
};

export default EntrancePage;
