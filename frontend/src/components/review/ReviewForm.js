import { useState } from "react";
import Rating from "./Rating";
import TextArea from "./TextArea";
import ImageUpload from "./ImageUpload";
import { API_URL } from "../../lib/constants";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const ReviewForm = (props) => {
  const navigate = useNavigate()
  const user = JSON.parse(window.localStorage.getItem("User"));
  const [reviewContent, setReviewContent] = useState("");
  const [imageList, setImageList] = useState([]);
  const [reviewScore, setReviewScore] = useState(0);
  const onRegistButtonClick = () => {
    const formData = new FormData();
    imageList.forEach((file) => formData.append("file", file.file));
    axios
      .post(`${API_URL}/review/add/img`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((res) => {
        console.log(res);

        const data = {
          userCode: user.userCode,
          stCode: props.history.stCode,
          puCode: props.history.puCode,
          reTitle:'',
          reContent: reviewContent,
          reImg: res.data.data,
          reScore: reviewScore,
        };

        console.log(data)
        axios
          .post(
            `${API_URL}/review/add`,
            JSON.stringify(data),
            { headers: { "Content-Type": "application/json" } }
          )
          .then((res) => {console.log(res); navigate('/history') })
          .catch((err) => console.log(err));
      })
      .catch((err) => console.log(err));
  };
  return (
    <div className="flex flex-col items-center p-5 gap-3">
      <Rating setReviewScore={setReviewScore} />

      <div className="flex flex-col w-full">
        <label className="self-start mb-1 flex items-center gap-1">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="1em"
            viewBox="0 0 512 512"
          >
            <path d="M471.6 21.7c-21.9-21.9-57.3-21.9-79.2 0L362.3 51.7l97.9 97.9 30.1-30.1c21.9-21.9 21.9-57.3 0-79.2L471.6 21.7zm-299.2 220c-6.1 6.1-10.8 13.6-13.5 21.9l-29.6 88.8c-2.9 8.6-.6 18.1 5.8 24.6s15.9 8.7 24.6 5.8l88.8-29.6c8.2-2.7 15.7-7.4 21.9-13.5L437.7 172.3 339.7 74.3 172.4 241.7zM96 64C43 64 0 107 0 160V416c0 53 43 96 96 96H352c53 0 96-43 96-96V320c0-17.7-14.3-32-32-32s-32 14.3-32 32v96c0 17.7-14.3 32-32 32H96c-17.7 0-32-14.3-32-32V160c0-17.7 14.3-32 32-32h96c17.7 0 32-14.3 32-32s-14.3-32-32-32H96z" />
          </svg>
          게시글 작성
        </label>
        <TextArea
          height="h-[150px]"
          inputPlaceholder="음식에 대한 후기를 남겨보세요!"
          setReviewContent={setReviewContent}
          reviewContent={reviewContent}
        />
      </div>
      <div className="flex flex-col w-full">
        <label className="self-start mb-1 flex items-center gap-1">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="1em"
            viewBox="0 0 512 512"
          >
            <path d="M448 80c8.8 0 16 7.2 16 16V415.8l-5-6.5-136-176c-4.5-5.9-11.6-9.3-19-9.3s-14.4 3.4-19 9.3L202 340.7l-30.5-42.7C167 291.7 159.8 288 152 288s-15 3.7-19.5 10.1l-80 112L48 416.3l0-.3V96c0-8.8 7.2-16 16-16H448zM64 32C28.7 32 0 60.7 0 96V416c0 35.3 28.7 64 64 64H448c35.3 0 64-28.7 64-64V96c0-35.3-28.7-64-64-64H64zm80 192a48 48 0 1 0 0-96 48 48 0 1 0 0 96z" />
          </svg>
          이미지 업로드
        </label>
        <ImageUpload imageList={imageList} setImageList={setImageList} />
      </div>

      <div className="fixed w-full flex flex-col bg-white bottom-0 items-center justify-center px-5 py-7 gap-2">
        <button
          className="btn h-[40px] w-full bg-myprimary text-xl text-white"
          onClick={() => onRegistButtonClick()}
        >
          리뷰 등록
        </button>
      </div>
    </div>
  );
};

export default ReviewForm;
