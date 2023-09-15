import StampCard from "./StampCard";

const Carousel = (props) => {
    return (
      <div>
        <div className="text-xl font-bold">{props.region}</div>
        <div className="text-base text-outline">1/10</div>
        <div className="carousel carousel-center w-full h-[460px] p-4 space-x-4">
          {props.stampCardList.map((stamp, index) => (
            <div className="carousel-item !ml-10 w-[310px]">
              <StampCard stamp={stamp} index={index}/>
            </div>
          ))}
        </div>
      </div>
    );
  };
  
  export default Carousel;