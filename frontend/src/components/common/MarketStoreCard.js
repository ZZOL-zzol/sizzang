const MarketStoreCard = () => {
  return (
    <div class="card card-side bg-base-100 rounded-none border-b-2">
      <div class="card-body p-3 justify-between">
        <div className="gap-0 flex flex-col">
          <div class="card-title text-base">가게 이름</div>
          <span className="text-sm text-left">서울시 관악구 봉천동</span>
        </div>

        <div className='text-left text-sm'>
            500m
        </div>
      </div>
      <figure className="m-3 rounded-lg w-24 h-24">
        <img className="w-full h-full" src="./chacha2.jpg" alt="Movie" />
      </figure>
    </div>
  );    
};

export default MarketStoreCard;
