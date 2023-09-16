import { useEffect, useState } from "react";
import CarouselCard from "./CarouselCard";
import axios from "axios";
import { API_URL } from "../../lib/constants";

const Carousel = () => {
  const [recommendedFoodList, setRecommendedFoodList] = useState([]);
  const [recommendedFoodComment, setRecommendedFoodComment] = useState([]);

  useEffect(() => {
    axios.get(`${API_URL}/marketprice/find/items`)
    .then((res) => {
      setRecommendedFoodList(res.data.data);
      axios.post(
        `${API_URL}/chatgpt/recommendMenu`,
        JSON.stringify({
          ingredients: [
            res.data.data[0].productName,
            res.data.data[1].productName,
            res.data.data[2].productName,
          ],
        }),
        { headers: { "Content-Type": "application/json" } }
      )
      .then(res => setRecommendedFoodComment(res.data))
      .catch(err => console.log(err));
    })
    .catch(err=>console.log(err));
  }, []);
  return (
    <div className="carousel carousel-center w-full p-4 space-x-4 bg-white">
      {recommendedFoodList.map((food, index) => (
        <div className="carousel-item" key={index}>
          <CarouselCard food={food} index={index} recommendedFoodComment={recommendedFoodComment[index]}/>
        </div>
      ))}
    </div>
  );
};

export default Carousel;
