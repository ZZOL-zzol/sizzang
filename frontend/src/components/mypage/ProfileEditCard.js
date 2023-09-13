import React, { useState } from "react";
import ImageUpload from "./ImageUpload";
import { useNavigate } from "react-router-dom";

/* 프로필 사진 */

/* 유저 정보 */
const UserInfo = ({ marketName, userName, setOpenProfileEdit }) => {
  return (
    <div>
      <div className="text-left text-lg font-bold">{marketName}</div>
      <div className="text-left text-xl font-bold">{userName}</div>
      <div
        className="text-left text-sm text-outline"
        onClick={() => setOpenProfileEdit(true)}
      >
        내 정보 수정 &gt;
      </div>
    </div>
  );
};

const ProfileEditCard = (props) => {
  const navigate = useNavigate();
  const [nickname, setNickname] = useState(props.user.userNickname);
  const [imageList, setImageList] = useState(props.user.profileImage);
  const onInputChange = (e) => {
    setNickname(e.target.value);
  };

  const onEditButtonClick = () => {
     
  }

  return (
    <div className="w-full">
      <div className="flex flex-col items-center gap-3">
        <div className="flex w-full justify-between">
          <button
            className="btn btn-ghost normal-case text-xl font-environmentR"
            onClick={()=>props.setOpenProfileEdit(false)}
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="1em"
              viewBox="0 0 384 512"
            >
              <path d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z" />
            </svg>
          </button>
          <button
            className="text-myerror underline"
            onClick={() => navigate("/")}
          >
            로그아웃
          </button>
        </div>
        <ImageUpload />
        <div>
          <input
            className="bg-background-fill rounded-lg px-2 py-1 focus:outline-myprimary w-32 font-bold text-lg"
            value={props.user.userNickname}
            onChange={onInputChange}
          />
          <div>{props.user.userName}</div>
          <div>{props.user.role}</div>
          <div>{props.user.role}</div>
        </div>

        <div className="w-full flex flex-col bg-white bottom-0 items-center justify-center px-5 gap-2">
        <button
          className="btn h-[40px] w-full bg-myprimary text-xl text-white"
          onClick={() => onEditButtonClick()}
        >
          수정 완료
        </button>
      </div>
      </div>

      
    </div>
  );
};

export default ProfileEditCard;
