import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Button from "../components/common/Button";
import TextInput from "../components/common/TextInput";
import axios from "axios";
import { API_URL } from "../lib/constants";

const EntrancePage = () => {
  const navigate = useNavigate()
  const [userId, setUserId] = useState('');
  const [userPassword, setUserPassword] = useState('');

  const onUserIdChange = (e) => {
    setUserId(e.target.value)
  };

  const onUserPasswordChange = (e) => {
    setUserPassword(e.target.value)
  }


  const onLoginClick = () => {
    axios
      .get(
        `${API_URL}/user/login`,
        JSON.stringify({ userId: userId, userPassword: userPassword }),
        {
          headers: { "Content-Type": "application/json" },
        }
      )
      .then((res) => {
        if (res.data) {
          window.localStorage.setItem('Access_Token',res.data.ownJwtAccessToken);
          window.localStorage.setItem('Refresh_Token',res.data.ownJwtRefreshToken);
          // window.localStorage.setItem()
          
        }
      })
      .then(
        navigate('/main')
      );
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
          <TextInput placeholder="ID" onChangeEvent={onUserIdChange} value={userId}/>
          <TextInput placeholder="PASSWORD" onChangeEvent={onUserPasswordChange} value={userPassword}/>
        </div>
        <div className="flex flex-col gap-2 w-full items-center px-9">
          {/* <Link to={"/main"} className="w-full"> */}
            <Button
              innerText="로그인"
              color="bg-blue-200"
              onClick={() => onLoginClick}
            />
          {/* </Link> */}
          <Link to={"/signup"} className="w-full">
            <Button innerText="회원가입" color="bg-blue-500" />
          </Link>
        </div>
      </div>
    </div>
  );
};

export default EntrancePage;
