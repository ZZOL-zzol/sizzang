import StampCard from "./StampCard";

const Carousel = (props) => {
    return (
      <div className="carousel carousel-center w-full h-[460px] p-4 space-x-4">
        {props.stampCardList.map((stamp, index) => (
          <div className="carousel-item !ml-10 w-[310px]">
            <StampCard stamp={stamp} index={index}/>
          </div>
        ))}
      </div>
    );
  };
  
  export default Carousel;