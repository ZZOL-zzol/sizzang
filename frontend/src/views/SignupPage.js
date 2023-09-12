import Button from "../components/common/Button";
import { Link, useNavigate } from "react-router-dom";
import TextInput from "../components/common/TextInput";
import Dropdown from "../components/common/Dropdown";
import axios from "axios";
import { API_URL } from "../lib/constants";
import { useState } from "react";

const SignupPage = () => {
  const navigate = useNavigate();
  const [userId, setUserId] = useState('');
  const [userPassword, setUserPassword] = useState('');
  const [userName, setUserName] = useState('');
  const [userNickname, setUserNickname] = useState('');
  const [role, setRole] = useState('none');

  const onUserIdChange = (e) => {
    setUserId(e.target.value)
  };

  const onUserPasswordChange = (e) => {
    setUserPassword(e.target.value)
  }

  const onUserNameChange = (e) => {
    setUserName(e.target.value)
  }

  const onUserNicknameChange = (e) => {
    setUserNickname(e.target.value)
  }

  const onRoleChange = (e) => {
    setRole(e.target.value)
    console.log(role)
  }

  const signUpClick = () => {

    axios
      .post(
        `${API_URL}/users/signup`,
        JSON.stringify({userName : userName,
        userId : userId,        
        userNickname : userNickname, 
        userPassword : userPassword,
        role : role}),
        {
          headers: { "Content-Type": "application/json" },
        }
      )
      .then((res) => {
          console.log(res);
          // window.localStorage.setItem()
          navigate('/')
        })
      .catch(err=> console.log(err)
      );
  };

    return(
        <div className=" w-full h-full bg-white outline outline-1">
      <div className="flex w-full h-2/5 items-center justify-center">
        <div>
          <img src="./ZZang_logo_text.png" alt="logo"/>
        </div>
      </div>
      <div className="flex flex-col h-3/5 justify-evenly  items-center">
        <div className="flex flex-col gap-2 w-full px-9">
          <TextInput placeholder="ID" onChangeEvent={onUserIdChange} value={userId}/>
          <TextInput placeholder="PASSWORD" onChangeEvent={onUserPasswordChange} value={userPassword}/>
        </div>
        <div className="flex flex-col gap-2 w-full px-9">
          <TextInput placeholder="이름" onChangeEvent={onUserNameChange} value={userName}/>
          <TextInput placeholder="닉네임" onChangeEvent={onUserNicknameChange} value={userNickname}/>
        </div>
        <div className="flex flex-col gap-2 w-full px-9">
          <Dropdown value={role} onChangeEvent={onRoleChange}/>
          {/* <TextInput placeholder="닉네임" onChangeEvent={onUserNameChange} value={userName}/> */}
        </div>
        <div className="flex flex-col gap-2 w-full items-center px-9">
          {/* <Link to={"/main"} className="w-full"> */}
            <Button innerText='시작하기' color='bg-blue-200' onClick={signUpClick}/>
          {/* </Link> */}
        </div>
      </div>
    </div>
    )
};

export default SignupPage;