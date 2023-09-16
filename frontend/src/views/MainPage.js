import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import Button from "../components/common/Button";
import { setCurrentTab } from "../store";

const MainPage = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  return (
    <div className=" w-full h-full bg-white outline outline-1">
      <div className="flex w-full h-3/5 items-center justify-center">
        <div>
          <img src="./ZZang_logo.svg" alt="logo" />
        </div>
      </div>
      <div className="flex flex-col h-2/5 justify-evenly  items-center">
        <div className="w-full px-10">
          <Button
            type = 'main'
            innerText="시장 찾기"
            color="bg-error-container"
            image="./market.svg"
            onClick={()=>{navigate('/place'); dispatch(setCurrentTab(1))}}
          />
          </div>
          <div className="w-full px-10">
          <Button
            type = 'main'
            innerText="상품 찾기"
            color="bg-primary-container"
            image="./product.svg"
            onClick={()=>{navigate('/product'); dispatch(setCurrentTab(2))}}
          />
          </div>
          <div className="w-full px-10">
          <Button
            type = 'main'
            innerText="마이페이지"
            color="bg-secondary-container"
            image="./mypage.svg"
            onClick={()=>{navigate('/profile'); dispatch(setCurrentTab(3))}}
          />
          </div>
      </div>
    </div>
  );
};

export default MainPage;
