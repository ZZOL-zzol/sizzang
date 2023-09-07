import { Link } from "react-router-dom";
// import { useSelector } from "react-redux/es/hooks/useSelector";
// import { useDispatch } from "react-redux";
import Button from "../components/common/Button";

const MainPage = () => {
  // const exampleState = useSelector((state) => state.exampleState.value);
  // const dispatch = useDispatch();

  return (
    <div className=" w-full h-full bg-white outline outline-1">
      <div className="flex w-full h-3/5 items-center justify-center">
        <div>
          <img src="./ZZang_logo.svg" alt="logo" />
        </div>
      </div>
      <div className="flex flex-col h-2/5 justify-evenly  items-center">
        <Link to='/market' className="w-full px-9">
          <Button
            innerText="장 보러 가기"
            color="bg-red-100"
            image="./market.svg"
          />
          </Link >
          <Link to='/product' className="w-full px-9">
          <Button
            innerText="농산물 시세 확인"
            color="bg-red-100"
            image="./product.svg"
          />
          </Link>
          <Link to='/profile' className="w-full px-9">
          <Button
            innerText="마이페이지"
            color="bg-red-100"
            image="./mypage.svg"
          />
          </Link>
      </div>
    </div>
  );
};

export default MainPage;
