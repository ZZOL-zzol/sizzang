import React, { useEffect, useRef, useState } from "react";
import axios from "axios";

const ImageUpload = () => {
  // 업로드할 파일들을 담을 State!

  const [imageList, setImageList] = useState([{ url: "../chacha2.jpg" }]);
  const imageInput = useRef(null);

  const onUploadButtonClicked = () => {
    if (imageInput.current) {
      imageInput.current.click();
    }
  };

  const handleImage = (e) => {
    console.log(e);
    const temp = [];
    const imageToAdd = e.target.files;
    if (imageToAdd) {
      for (let i = 0; i < imageToAdd.length; i++) {
        const tmpImageFile = {
          id: imageToAdd[i].name,
          file: imageToAdd[i],
          url: URL.createObjectURL(imageToAdd[i]),
        };

        temp.push(tmpImageFile);
      }
      console.log(temp);
      if (temp.length > 0) {
        setImageList(temp.concat(imageList));
      }
    }
  };

  return (
    <>
      <div className="flex overflow-auto">
        <input
          type="file"
          accept="image/jpg, image/jpeg, image/png"
          multiple
          ref={imageInput}
          style={{ display: "none" }}
          onChange={(e) => handleImage(e)}
        />
        {imageList.map((image) => (
          <div
            key={image.url}
            className="relative w-full pb-[100%] bg-gray-100 rounded-lg flex flex-col justify-center"
          >
            <img
              src={image.url}
              alt={image.id}
              className="absolute w-full h-full rounded-lg z-0 top-0"
            ></img>

            <div
              className="absolute z-10 w-full h-full flex top-0 items-center justify-center rounded-lg bg-black opacity-0 hover:opacity-30 transition-opacity"
              onClick={onUploadButtonClicked}
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                height="3em"
                viewBox="0 0 512 512"
                className="fill-white"
              >
                <path d="M471.6 21.7c-21.9-21.9-57.3-21.9-79.2 0L362.3 51.7l97.9 97.9 30.1-30.1c21.9-21.9 21.9-57.3 0-79.2L471.6 21.7zm-299.2 220c-6.1 6.1-10.8 13.6-13.5 21.9l-29.6 88.8c-2.9 8.6-.6 18.1 5.8 24.6s15.9 8.7 24.6 5.8l88.8-29.6c8.2-2.7 15.7-7.4 21.9-13.5L437.7 172.3 339.7 74.3 172.4 241.7zM96 64C43 64 0 107 0 160V416c0 53 43 96 96 96H352c53 0 96-43 96-96V320c0-17.7-14.3-32-32-32s-32 14.3-32 32v96c0 17.7-14.3 32-32 32H96c-17.7 0-32-14.3-32-32V160c0-17.7 14.3-32 32-32h96c17.7 0 32-14.3 32-32s-14.3-32-32-32H96z" />
              </svg>
            </div>
          </div>
        ))}
        {/* {uploadButton} */}
      </div>
      {/* <button onClick={fileUploadHandler}>파일 업로드 하기</button> */}
    </>
  );
};

export default ImageUpload;
