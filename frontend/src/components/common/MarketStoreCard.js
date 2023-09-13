import { useNavigate } from "react-router-dom";

const MarketStoreCard = (props) => {
  const navigate = useNavigate();

  const navigateTo = () => {
    if (props.market) {
      navigate("/market/" + props.market.mkCode);
    } else {
      navigate("/store/" + props.store.stCode);
    }
  };

  return (
    <div
      className="card card-side bg-base-100 rounded-none border-b-2"
      onClick={() => navigateTo()}
    >
      {props.market ? (
        <div className="flex w-full">
          <div className="card-body p-3 justify-between">
            <div className="gap-0 flex flex-col">
              <div className="card-title text-base">{props.market.mkName}</div>
              <span className="text-sm text-left">{props.market.mkAddress}</span>
            </div>

            <div className="text-left text-sm">{props.market.mkPhone}</div>
          </div>
          <figure className="m-3 rounded-lg w-24 h-24">
            <img className="w-full h-full" src={props.market.mkImg} alt="marketImage" />
          </figure>
        </div>
      ) : (
        <div className="flex w-full">
          <div className="card-body p-3 justify-between">
            <div className="gap-0 flex flex-col">
              <div className="flex w-full justify-between"><div className="card-title text-base">{props.store.stName}</div><div className="text-base">별점</div></div>
              <span className="text-sm text-left">{props.store.scName}</span>
            </div>

            <div className="text-left text-sm">{props.store.stAddress}</div>
          </div>
          <figure className="m-3 rounded-lg w-24 h-24">
            <img className="w-full h-full" src={props.store.stImg} alt="storeImage" />
          </figure>
        </div>
      )}
    </div>
  );
};

export default MarketStoreCard;
