import { useNavigate } from "react-router-dom";
import SmallButton from "../common/SmallButton";

const HistoryCard = (props) => {
  const navigate = useNavigate();
  const onReviewButtonClick = () => {
    navigate("/review", { state: { history: props.history, accountNumber: props.accountNumber } });
  };
  return (
    <div className=" bg-white h-[80px] p-[10px]">
      <div className="w-full h-full flex flex-row justify-between">
        <div className="flex flex-col">
          <div className="card-title text-base">
            {props.history.details[0].prName}x{props.history.details[0].puCnt}{" "}
            외 {props.history.details.length}건{" "}
          </div>
          <span className="text-sm text-left">{props.history.puDate}</span>
        </div>
        <div className="flex flex-col h-full justify-between">
          {props.history.reRegisted ? (
            <SmallButton innerText="리뷰 완료" color="bg-outline-container" />
          ) : (
            <SmallButton
              innerText="리뷰 쓰기"
              color="bg-primary-container"
              onReviewButtonClick={onReviewButtonClick}
            />
          )}

          <div>{props.history.puCost}원</div>
        </div>
      </div>
    </div>
  );
};

export default HistoryCard;
