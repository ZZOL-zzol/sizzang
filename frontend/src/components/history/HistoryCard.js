import { useNavigate } from "react-router-dom";
import SmallButton from "../common/SmallButton";



const HistoryCard = (props) => {
  const navigate = useNavigate();
  const onReviewButtonClick = () => {
    navigate('/review', {state:{history : props.history}})
  }
  return (
    <div className=" bg-white h-[80px] p-[10px]">
      <div className="w-full h-full flex flex-row justify-between">
        <div className="flex flex-col">
        <div className="card-title text-base">{props.history.hsName}</div>
          <span className="text-sm text-left">{props.history.hsDate}</span>
        </div>
        <div className="flex flex-col h-full justify-between">
          {props.history.reviewed?  <div className="w-[64px]"></div> : <SmallButton innerText="리뷰 쓰기" color="bg-primary-container" onReviewButtonClick={onReviewButtonClick}/>}
          
          <div>{props.history.hsCost}</div>
        </div>
      </div>
    </div>
  );
};

export default HistoryCard;
