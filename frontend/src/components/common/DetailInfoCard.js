const DetailInfoCard = (props) => {
  return (
    <div class="card card-side bg-base-100 shadow-xl mx-5 h-[120px]">
      {props.market ? (
        <div class="card-body p-3 justify-between">
          <div className="gap-0 flex flex-col">
            <div class="card-title text-xl mx-auto">{props.market.mkName}</div>
            <span className="text-sm">{props.market.mkInfo} </span>
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
            {props.market.mkScore}
          </div>
        </div>
      ) : (
        <div class="card-body p-3 justify-between">
          <div className="gap-0 flex flex-col">
            <div class="card-title text-xl mx-auto">{props.store.stName}</div>
            <span className="text-sm">{props.store.stIntro} </span>
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
           {props.store.stScore}
          </div>
        </div>
      )}
    </div>
  );
};

export default DetailInfoCard;
