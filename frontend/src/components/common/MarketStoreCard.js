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
              <div className="flex w-full justify-between"><div className="card-title text-base">{props.store.stName}</div><div className="text-base"></div></div>
              <span className="text-sm text-left">{props.store.stLongtitude}</span>
            </div>

            <div className="text-left text-sm">{props.store.scName}</div>
          </div>
          <div className="text-sm flex mx-auto items-center">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="1em"
              viewBox="0 0 576 512"
              className="fill-yellow-300"
            >
              a
              <path d="M316.9 18C311.6 7 300.4 0 288.1 0s-23.4 7-28.8 18L195 150.3 51.4 171.5c-12 1.8-22 10.2-25.7 21.7s-.7 24.2 7.9 32.7L137.8 329 113.2 474.7c-2 12 3 24.2 12.9 31.3s23 8 33.8 2.3l128.3-68.5 128.3 68.5c10.8 5.7 23.9 4.9 33.8-2.3s14.9-19.3 12.9-31.3L438.5 329 542.7 225.9c8.6-8.5 11.7-21.2 7.9-32.7s-13.7-19.9-25.7-21.7L381.2 150.3 316.9 18z" />
            </svg>
            <div>
           {props.store.reScore ? props.store.reScore.toFixed(1) : props.store.reScore}
           ({props.store.reCnt})
            </div>
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
