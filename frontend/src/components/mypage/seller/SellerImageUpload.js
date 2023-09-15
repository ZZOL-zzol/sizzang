import React, { useEffect, useRef, useState } from "react";
import axios from "axios";

const SellerImageUpload = (props) => {

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
        props.setImageList(temp.concat(props.imageList));
      }
    }
  };

  const uploadButton = (
    <div
      className="relative w-full pb-[50%] bg-gray-100 rounded-lg flex flex-col justify-center"
      onClick={onUploadButtonClicked}
    >
      <div className="absolute top-[37%] left-[44%] flex flex-col items-center">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          height="3em"
          viewBox="0 0 448 512"
          className="fill-surface"
        >
          <path d="M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32V224H48c-17.7 0-32 14.3-32 32s14.3 32 32 32H192V432c0 17.7 14.3 32 32 32s32-14.3 32-32V288H400c17.7 0 32-14.3 32-32s-14.3-32-32-32H256V80z" />
        </svg>
        <div className="mt-2">업로드</div>
      </div>
    </div>
  );

  const onRemoveButtonClicked = () => {
    props.setImageList([]);
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
        {props.imageList.length>0? props.imageList.map((image) => (
          <div
          key={image.url}
          className="relative w-full pb-[50%] bg-gray-100 rounded-lg flex flex-col justify-center"
        >
          <img
            src={image.url}
            alt={image.id}
            className="absolute w-full h-full rounded-lg z-0 top-0"
          ></img>

          <div
            className="absolute z-10 w-full h-full flex top-0 items-center justify-center rounded-lg bg-black opacity-0 hover:opacity-30 transition-opacity"
            onClick={onRemoveButtonClicked}
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="3em"
              viewBox="0 0 448 512"
              className="fill-white"
            >
              <path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z" />
            </svg>
          </div>
        </div>
        )) : null}
        {props.imageList.length === 0 ? uploadButton : null}
      </div>
      {/* <button onClick={fileUploadHandler}>파일 업로드 하기</button> */}
    </>
  );
};

export default SellerImageUpload;
