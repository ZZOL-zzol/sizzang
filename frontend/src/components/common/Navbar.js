import { useState } from "react";
import { useSelector } from "react-redux/es/hooks/useSelector";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { setCurrentTab } from "../../store";

const Navbar = () => {
  const navigate = useNavigate();
  const currentTab = useSelector((state) => state.currentTab.value);
  const dispatch = useDispatch();

  return (
    <div className="btm-nav z-40">
      <button
        className={currentTab === 0 ? "border-t-2 border-myprimary" : ""}
        onClick={() => {
          navigate("/main");
          dispatch(setCurrentTab(0));
        }}
      >
        <svg
          className={currentTab === 0 ? "h-5 w-5 fill-myprimary" : "h-5 w-5"}
          xmlns="http://www.w3.org/2000/svg"
          height="1em"
          viewBox="0 0 576 512"
        >
          <path d="M575.8 255.5c0 18-15 32.1-32 32.1h-32l.7 160.2c0 2.7-.2 5.4-.5 8.1V472c0 22.1-17.9 40-40 40H456c-1.1 0-2.2 0-3.3-.1c-1.4 .1-2.8 .1-4.2 .1H416 392c-22.1 0-40-17.9-40-40V448 384c0-17.7-14.3-32-32-32H256c-17.7 0-32 14.3-32 32v64 24c0 22.1-17.9 40-40 40H160 128.1c-1.5 0-3-.1-4.5-.2c-1.2 .1-2.4 .2-3.6 .2H104c-22.1 0-40-17.9-40-40V360c0-.9 0-1.9 .1-2.8V287.6H32c-18 0-32-14-32-32.1c0-9 3-17 10-24L266.4 8c7-7 15-8 22-8s15 2 21 7L564.8 231.5c8 7 12 15 11 24z" />
        </svg>
      </button>
      <button
        className={currentTab === 1 ? "border-t-2 border-myprimary" : ""}
        onClick={() => {
          navigate("/place");
          dispatch(setCurrentTab(1));
        }}
      >
        <svg
          className={currentTab === 1 ? "h-5 w-5 fill-myprimary" : "h-5 w-5"}
          xmlns="http://www.w3.org/2000/svg"
          height="1em"
          viewBox="0 0 576 512"
        >
          <path d="M547.6 103.8L490.3 13.1C485.2 5 476.1 0 466.4 0H109.6C99.9 0 90.8 5 85.7 13.1L28.3 103.8c-29.6 46.8-3.4 111.9 51.9 119.4c4 .5 8.1 .8 12.1 .8c26.1 0 49.3-11.4 65.2-29c15.9 17.6 39.1 29 65.2 29c26.1 0 49.3-11.4 65.2-29c15.9 17.6 39.1 29 65.2 29c26.2 0 49.3-11.4 65.2-29c16 17.6 39.1 29 65.2 29c4.1 0 8.1-.3 12.1-.8c55.5-7.4 81.8-72.5 52.1-119.4zM499.7 254.9l-.1 0c-5.3 .7-10.7 1.1-16.2 1.1c-12.4 0-24.3-1.9-35.4-5.3V384H128V250.6c-11.2 3.5-23.2 5.4-35.6 5.4c-5.5 0-11-.4-16.3-1.1l-.1 0c-4.1-.6-8.1-1.3-12-2.3V384v64c0 35.3 28.7 64 64 64H448c35.3 0 64-28.7 64-64V384 252.6c-4 1-8 1.8-12.3 2.3z" />
        </svg>
      </button>
      <button
        className={currentTab === 2 ? "border-t-2 border-myprimary" : ""}
        onClick={() => {
          navigate("/product");
          dispatch(setCurrentTab(2));
        }}
      >
        <svg
          className={currentTab === 2 ? "h-5 w-5 fill-myprimary" : "h-5 w-5"}
          xmlns="http://www.w3.org/2000/svg"
          height="1em"
          viewBox="0 0 448 512"
        >
          <path d="M160 112c0-35.3 28.7-64 64-64s64 28.7 64 64v48H160V112zm-48 48H48c-26.5 0-48 21.5-48 48V416c0 53 43 96 96 96H352c53 0 96-43 96-96V208c0-26.5-21.5-48-48-48H336V112C336 50.1 285.9 0 224 0S112 50.1 112 112v48zm24 48a24 24 0 1 1 0 48 24 24 0 1 1 0-48zm152 24a24 24 0 1 1 48 0 24 24 0 1 1 -48 0z" />
        </svg>
      </button>
      <button
        className={currentTab === 3 ? "border-t-2 border-myprimary" : ""}
        onClick={() => {
          navigate("/profile");
          dispatch(setCurrentTab(3));
        }}
      >
        <svg
          className={currentTab === 3 ? "h-5 w-5 fill-myprimary" : "h-5 w-5"}
          xmlns="http://www.w3.org/2000/svg"
          height="1em"
          viewBox="0 0 448 512"
        >
          <path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z" />
        </svg>
      </button>
    </div>
  );
};

export default Navbar;
