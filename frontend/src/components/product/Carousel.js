import CarouselCard from "./CarouselCard";


const RecommendedFoodList = [
  {product : '대패삼겹살', price : 1000, recommended: '대패삼겹볶음밥', priceChange : '-2000'},
  {product : '대패삼겹살', price : 1000, recommended: '대패삼겹볶음밥', priceChange : '-2000'},
  {product : '대패삼겹살', price : 1000, recommended: '대패삼겹볶음밥', priceChange : '-2000'},
  {product : '대패삼겹살', price : 1000, recommended: '대패삼겹볶음밥', priceChange : '-2000'},

]


const Carousel = (props) => {
  return (
    <div className="carousel carousel-center w-full p-4 space-x-4 bg-white">
      {RecommendedFoodList.map((food, index) => (
        <div className="carousel-item" key={index}>
          <CarouselCard food={food} index={index}/>
        </div>
      ))}
    </div>
  );
};

export default Carousel;
