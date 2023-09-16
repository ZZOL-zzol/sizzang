import StampCard from "./StampCard";

const Carousel = (props) => {
  console.log(props.stampCardList);
  return (
    <div>
      <div className="text-base text-outline">1/10</div>
      <div className="carousel carousel-center w-screen p-4 space-x-4 h-[600px] rounded-box pr-10">
        {props.stampCardList.map((stamp, index) => (
          <div className="carousel-item w-[310px] snap-center">
            <StampCard stamp={stamp} index={index} />
          </div>
        ))}
      </div>
    </div>
  );
};

export default Carousel;
