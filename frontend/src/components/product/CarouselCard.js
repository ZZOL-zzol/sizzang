import { useState } from "react";

const CarouselCard = (props) => {
  const [clicked ,setClicked] = useState(false)
  return (
    <div
      tabIndex={0}
      className="collapse border border-base-300 bg-background-fill w-[330px] h-min"
      onClick={()=>setClicked(!clicked)}
    >
      <input type="checkbox"/>
      <div className="collapse-title text-xl font-medium flex flex-col !px-5 !pb-0">
        <div className="flex justify-between">
          <div className="font-medium text-xl flex items-center">
            {props.index + 1}. {props.food.productName} ({props.food.unit})
          </div>
          <div className="flex items-center">
            <div className="font-bold text-2xl">{props.food.dpr1}</div>
            <div className="font-normal text-lg">원</div>
            <div className="font-normal text-base text-myprimary">
              ▼{props.food.dpr3-props.food.dpr1}
            </div>
          </div>
        </div>
        <div className="flex justify-center items-center">
          {clicked?null:<svg
            xmlns="http://www.w3.org/2000/svg"
            height="1.3em"
            viewBox="0 0 320 512"
            className="fill-white"
          >
            <path d="M137.4 374.6c12.5 12.5 32.8 12.5 45.3 0l128-128c9.2-9.2 11.9-22.9 6.9-34.9s-16.6-19.8-29.6-19.8L32 192c-12.9 0-24.6 7.8-29.6 19.8s-2.2 25.7 6.9 34.9l128 128z" />
          </svg>}
        </div>
      </div>
      <div className="collapse-content text-base text-left">{props.recommendedFoodComment}</div>
    </div>
//     <details className="collapse bg-base-200">
//   <summary className="collapse-title text-xl font-medium">Click to open/close</summary>
//   <div className="collapse-content"> 
//     <p>content</p>
//   </div>
// </details>
  );
};

export default CarouselCard;
