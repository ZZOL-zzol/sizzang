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
        <div className="card-title text-base">{props.history.hsName}</div>
          
          <span className="text-sm">{props.history.hsDate}</span>
        </div>
        <div className="flex flex-col h-full self-end">
          
          <div>{props.history.hsCost}</div>
        </div>
      </div>
    </div>
  );
};

export default ReviewCard;
