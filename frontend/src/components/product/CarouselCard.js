const CarouselCard = (props) => {
  return (
    <div className="rounded-[20px] w-[330px] h-[150px] bg-background-fill p-5">
      <div className="flex justify-between items-baseline">
        <div className="font-medium text-xl">{props.index+1}. {props.food.product}</div>
        <div className="flex items-end">
          <div className="font-bold text-2xl">{props.food.price}</div>
          <div className="font-normal text-lg">원</div>
          <div className="font-normal text-base text-myprimary ml-1 mb-0.5">
            ▼{props.food.priceChange}
          </div>
        </div>
      </div>
      <div className="flex justify-center mt-3">
        <div className="flex text-lg font-bold items-center">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="1em"
            viewBox="0 0 448 512"
            className="fill-green-500 mr-1"
          >
            <path d="M416 0C400 0 288 32 288 176V288c0 35.3 28.7 64 64 64h32V480c0 17.7 14.3 32 32 32s32-14.3 32-32V352 240 32c0-17.7-14.3-32-32-32zM64 16C64 7.8 57.9 1 49.7 .1S34.2 4.6 32.4 12.5L2.1 148.8C.7 155.1 0 161.5 0 167.9c0 45.9 35.1 83.6 80 87.7V480c0 17.7 14.3 32 32 32s32-14.3 32-32V255.6c44.9-4.1 80-41.8 80-87.7c0-6.4-.7-12.8-2.1-19.1L191.6 12.5c-1.8-8-9.3-13.3-17.4-12.4S160 7.8 160 16V150.2c0 5.4-4.4 9.8-9.8 9.8c-5.1 0-9.3-3.9-9.8-9L127.9 14.6C127.2 6.3 120.3 0 112 0s-15.2 6.3-15.9 14.6L83.7 151c-.5 5.1-4.7 9-9.8 9c-5.4 0-9.8-4.4-9.8-9.8V16zm48.3 152l-.3 0-.3 0 .3-.7 .3 .7z" />
          </svg>
          추천요리
        </div>
      </div>
      <div className="flex justify-center mt-3">
        <div className="flex bg-white rounded-full h-5 w-fit h-auto text-base px-2 py-1 items-center">
          {props.food.recommended}
        </div>
      </div>
    </div>
  );
};

export default CarouselCard;