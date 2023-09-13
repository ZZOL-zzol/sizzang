import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Button from "../components/common/Button";
import TextInput from "../components/common/TextInput";
import axios from "axios";
import { API_URL } from "../lib/constants";

const EntrancePage = () => {
  const navigate = useNavigate();
  const [userId, setUserId] = useState("");
  const [userPassword, setUserPassword] = useState("");

  const onUserIdChange = (e) => {
    setUserId(e.target.value);
  };

  const onUserPasswordChange = (e) => {
    setUserPassword(e.target.value);
  };

  const onLoginClick = () => {
    axios
      .post(
        `${API_URL}/login`,
        JSON.stringify({ userId: userId, userPassword: userPassword }),
        {
          headers: { "Content-Type": "application/json" },
        }
      )
      .then((res) => {
        console.log(res);
        if (res.headers["authorization"]) {
          window.localStorage.setItem(
            "Access_Token",
            res.headers["authorization"]
          );
          window.localStorage.setItem(
            "Refresh_Token",
            res.headers["authorization-refresh"]
          );
          window.localStorage.setItem("User", JSON.stringify({
            userCode : res.data.userCode,
            userId : res.data.userId,
            userName: res.data.userName,
            userNickname: res.data.userNickname,
            userAccount: res.data.userAccount,
            role: res.data.role,
            stamp: res.data.stamp,
          }));
          navigate("/main");
        }
      })
      .catch((err) => console.log(err));
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
          <TextInput
            placeholder="ID"
            onChangeEvent={onUserIdChange}
            value={userId}
          />
          <TextInput
            placeholder="PASSWORD"
            onChangeEvent={onUserPasswordChange}
            value={userPassword}
          />
        </div>
        <div className="flex flex-col gap-2 w-full items-center px-9">
          <Button
            innerText="로그인"
            color="bg-blue-200"
            onClick={onLoginClick}
          />
          <Link to={"/signup"} className="w-full">
            <Button innerText="회원가입" color="bg-blue-500" />
          </Link>
        </div>
      </div>
    </div>
  );
};

export default EntrancePage;
