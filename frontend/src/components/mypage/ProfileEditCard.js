import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { API_URL } from "../../lib/constants";
import SellerImageUpload from "./seller/SellerImageUpload";
import ConsumerImageUpload from "./consumer/ConsumerImageUpload";
import EditInput from "./seller/EditInput";

/* 프로필 사진 */

/* 유저 정보 */
const ProfileEditCard = (props) => {
  const navigate = useNavigate();
  let user = props.user
  const [nickname, setNickname] = useState(props.user.userNickname);
  const [imageList, setImageList] = useState([]);
  const [editMarket, setEditMarket] = useState(false);
  const [editStore, setEditStore] = useState(false);

  useEffect(() => {
    console.log(props.user);
    if (props.user.userImgUrl) {
      setImageList(props.user.userImgUrl);
    }
  }, []);

  const onInputChange = (e) => {
    setNickname(e.target.value);
  };

  const onEditButtonClick = () => {
    const formData = new FormData();
    imageList.forEach((file) => formData.append("file", file.file));
    axios
      .post(`${API_URL}/users/selectImg`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((res) => {
        console.log(res);
        axios
          .put(
            `${API_URL}/users/registUserImg`,
            JSON.stringify({
              userId: props.user.userId,
              userImgUrl: res.data.data,
            }),
            { headers: { "Content-Type": "application/json" } }
          )
          .then((res) => console.log(res))
          .catch((err) => console.losg(err));
      })
      .catch((err) => console.log(err));
  };

  const onSellerEditButtonClick = () => {

    console.log(user)
    console.log(imageList)
    const formData = new FormData();
    imageList.forEach((file) => formData.append("file", file.file));
    formData.append(
      'registInfo',
      new Blob([JSON.stringify({"scCode":1,
      "mkCode":1,
      "stName": user.stName,
      "stPhone": user.stPhone,
      "stImg": '',
      "stAccount": user.stAccount,
      "stIntro": user.stIntro,
      "stTime": user.stTime,
      "stLatitude": "37.4818538",
      "stLongtitude": "126.952325",
      "userCode":user.userCode,
      "stAddress":user.stAddress
      })], { type: 'application/json' })
    );
    axios
      .post(`${API_URL}/store`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => console.log(err));
  };

  const setUserValue = (e) => {
    user[e.target.name] = e.target.value
  }

  return (
    <div className="w-full">
      {props.user.role === "CUSTOMER" ? (
        <div className="flex flex-col items-center gap-3">
          <div className="flex w-full justify-between">
            <button
              className="btn btn-ghost normal-case text-xl font-environmentR"
              onClick={() => props.setOpenProfileEdit(false)}
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
              className="text-myerror underline mr-5"
              onClick={() => navigate("/")}
            >
              로그아웃
            </button>
          </div>
          <div className="w-[150px] h-[150px]">
            <ConsumerImageUpload
              imageList={imageList}
              setImageList={setImageList}
            />
          </div>
          <div>
            <input
              className="bg-background-fill rounded-lg px-2 py-1 focus:outline-myprimary w-32 font-bold text-lg"
              value={props.user.userNickname}
              onChange={onInputChange}
            />
            <div>{props.user.userName}</div>
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
      ) : (
        <div className="flex flex-col items-center gap-3 px-5">
          <div className="flex w-full justify-between">
            <button
              className="btn btn-ghost normal-case text-xl font-environmentR"
              onClick={() => props.setOpenProfileEdit(false)}
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
              className="text-myerror underline mr-5"
              onClick={() => navigate("/")}
            >
              로그아웃
            </button>
          </div>
          <div className="w-full">
            <SellerImageUpload
              imageList={imageList}
              setImageList={setImageList}
            />
          </div>
          <div className="bg-background-fill rounded-lg w-full flex flex-col p-5 gap-5">
            <div className="flex justify-between">
              <div className="flex text-lg font-bold">시장 정보</div>
              <button className="text-myprimary" onClick={() => setEditMarket(!editMarket)}>
                수정
              </button>
            </div>
            <div className="flex flex-col gap-2">
              <div className="flex justify-between">
                <div>시장명</div>
                {editMarket? <EditInput user={user} data='mkName' onInputChange={setUserValue}/>: <div className="text-outline">{props.user.mkName}</div>}
              </div>
              <div className="flex justify-between">
                <div>주소</div>
                {editMarket? <EditInput user={user} data='mkAddress' onInputChange={setUserValue}/>:<div className="text-outline">{props.user.mkAddress}</div>}
              </div>
            </div>
          </div>
          <div className="bg-background-fill rounded-lg w-full flex flex-col p-5 gap-5">
            <div className="flex justify-between">
              <div className="flex text-lg font-bold">점포 정보</div>
              <button className="text-myprimary" onClick={() => setEditStore(!editStore)}>
                수정
              </button>
            </div>
            <div className="flex flex-col gap-2">
              <div className="flex justify-between">
                <div>점포명</div>
                {editStore? <EditInput user={user} data='stName' onInputChange={setUserValue}/>:<div className="text-outline">{props.user.stName}</div>}
              </div>
              <div className="flex justify-between">
                <div>주소</div>
                {editStore? <EditInput user={user} data='stAddress' onInputChange={setUserValue}/>:<div className="text-outline">{props.user.stAddress}</div>}
              </div>
              <div className="flex justify-between">
                <div>카테고리</div>
                {editStore? <EditInput user={user} data='stCategory' onInputChange={setUserValue}/>:<div className="text-outline">{props.user.stCategory}</div>}
              </div>
              <div className="flex justify-between">
                <div>전화번호</div>
                {editStore? <EditInput user={user} data='stPhone' onInputChange={setUserValue}/>:<div className="text-outline">{props.user.stPhone}</div>}
              </div>
              <div className="flex justify-between">
                <div>영업시간</div>
                {editStore? <EditInput user={user} data='stTime' onInputChange={setUserValue}/>:<div className="text-outline">{props.user.stTime}</div>}
              </div>
              <div className="flex justify-between">
                <div>소개</div>
                {editStore? <EditInput user={user} data='stIntro' onInputChange={setUserValue}/>:<div className="text-outline">{props.user.stIntro}</div>}
              </div>
            </div>
          </div>  
          <div className="w-full flex flex-col bg-white bottom-0 items-center justify-center px-5 gap-2">
            <button
              className="btn h-[40px] w-full bg-myprimary text-xl text-white"
              onClick={() => onSellerEditButtonClick()}
            >
              저장하기
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default ProfileEditCard;
