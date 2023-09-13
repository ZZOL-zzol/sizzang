import MarketStoreCard from "../common/MarketStoreCard";
import { useLocation } from "react-router-dom";
import SmallButton from "../common/SmallButton";

const ProductCard = (props) => {
  const location = useLocation();
  const currentPage = location.pathname.split("/")[1];

  const addBasket = () => {
    window.localStorage.setItem("basket", {
      stCode: props.store.stCode,
      stName: props.store.stName,
      stAddress: props.store.stAddress,
      stPhone: props.store.stPhone,
      productList: [
        {
          pdCode: props.product.pdCode,
          pcCode: props.product.pcCode,
          pdName: props.product.pdName,
          pdCost: props.product.pdCost,
          pdIntro: props.product.pdIntro,
          count: 1,
        },
      ],
    });
  };

  const setProductCount = (pdCode, type) => {
    const basket = JSON.parse(window.localStorage.getItem("basket"));

    if (type === "plus") {
      basket.productList.map((product) =>
        product.pdCode === pdCode ? (product.count += 1) : null
      );
    } else {
      basket.productList.map((product, index) =>
        product.pdCode === pdCode && product.count === 1
          ? basket.productList.splice(index, 1)
          : product.pdCode === pdCode && product.count > 1
          ? (product.count -= 1)
          : null
      );
    }
  };
  return (
    <div className="card-body p-3 justify-between border-b-2 bg-white border-outline-container">
      <div className="gap-0 flex justify-between">
        <div className="flex flex-col">
          <div className="card-title text-base">{props.product.pdName}</div>
          <div className="text-base text-outline">{props.product.pdIntro}</div>
        </div>
        {/* <span className="text-sm text-left">{props.product.pdCost}</span> */}
        {props.type === "basket" ? (
          <div className="flex">
            <button className="btn btn-xs btn-circle bg-outline-container">
              -
            </button>
            <div className="mx-1">{props.product.count}</div>
            <button
              className="btn btn-xs btn-circle bg-myprimary"
              onClick={() => setProductCount(props.product.pdCode, "plus")}
            >
              +
            </button>
          </div>
        ) : (
          <SmallButton
            color="bg-primary-container"
            innerText="담기"
            onClick={() => addBasket()}
          />
        )}
      </div>

      <div className="flex justify-end items-center">
        <div className="mr-2">{props.product.pdCost}원</div>
        {props.type === "basket" ? null : (
          <div className="text-sm text-myerror">▲1000</div>
        )}
      </div>
    </div>
  );
};

export default ProductCard;
