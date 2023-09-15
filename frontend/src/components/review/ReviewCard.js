import { useNavigate } from "react-router-dom";
import SmallButton from "../common/SmallButton";



const ReviewCard = (props) => {
  const navigate = useNavigate();
  const onReviewButtonClick = () => {
    navigate('/review', {state:{history : props.history}})
  }
  return (
    <div className=" bg-white h-[80px] p-[10px]">
      <div className="w-full h-full flex flex-col items-center">
      
        <div className="flex flex-col">
        <div className="card-title text-base">{props.history.ppList[0].ppName}x{props.history.ppList[0].ppInt} 외 {props.history.ppList.length}건</div>
          
          <span className="text-sm">{props.history.puDate}</span>
        </div>
        <div className="flex flex-col h-full self-end">
          
          <div>{props.history.puCost}원</div>
        </div>
      </div>
    </div>
  );
};

export default ReviewCard;
