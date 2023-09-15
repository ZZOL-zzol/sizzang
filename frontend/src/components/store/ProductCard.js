import MarketStoreCard from "../common/MarketStoreCard";
import { useSelector } from "react-redux/es/hooks/useSelector";
import { useDispatch } from "react-redux";
import { setBasketCount } from "../../store";
import { useLocation } from "react-router-dom";
import SmallButton from "../common/SmallButton";

const ProductCard = (props) => {
  const location = useLocation();
  const currentPage = location.pathname.split("/")[1];
  const basketCount = useSelector((state) => state.basketCount.value);
  const dispatch = useDispatch();

  // console.log(props.product)
  const addBasket = () => {
    if (!window.localStorage.getItem("BasketProductList")) {
      console.log("비어있음");
      const basketStore = {
        stCode: props.store.stCode,
        stName: props.store.stName,
        stAddress: props.store.stAddress,
        stPhone: props.store.stPhone,
      };
      const basketProduct = {
        pdCode: props.product.pdCode,
        pcCode: props.product.pcCode,
        pdName: props.product.pdName,
        pdCost: props.product.pdCost,
        pdIntro: props.product.pdIntro,
        count: 1,
      };
      dispatch(setBasketCount(1));
      window.localStorage.setItem("BasketStore", JSON.stringify(basketStore));
      window.localStorage.setItem(
        "BasketProductList",
        JSON.stringify([basketProduct])
      );
    } else {
      dispatch(setBasketCount(basketCount + 1));
      let tmpBasketProductList = JSON.parse(window.localStorage.getItem('BasketProductList'))
      tmpBasketProductList.splice(tmpBasketProductList.length,1, {
        pdCode: props.product.pdCode,
        pcCode: props.product.pcCode,
        pdName: props.product.pdName,
        pdCost: props.product.pdCost,
        pdIntro: props.product.pdIntro,
        count: 1,
      })

      window.localStorage.setItem(
        "BasketProductList",
        JSON.stringify(tmpBasketProductList)
      );
    }
  };

  const setProductCount = (pdCode, type) => {
    let basketProductList = JSON.parse(JSON.stringify(props.basketProductList));
    if (type === "plus") {
      basketProductList.map((product, index) =>
        product.pdCode === pdCode ? (product.count += 1) : null
      );
    } else {
      basketProductList.map((product, index) =>
        product.pdCode === pdCode && product.count === 1
          ? basketProductList.splice(index, 1)
          : product.pdCode === pdCode && product.count > 1
          ? (product.count -= 1)
          : null
      );
    }
    props.setBasketProductList(basketProductList);
    if (props.basketProductList.length === 0) {
      props.setBasketStore({});
      window.localStorage.setItem("BasketStore", "{}");
    }
    window.localStorage.setItem(
      "BasketProductList",
      JSON.stringify(basketProductList)
    );

    props.setBasketTotalCost(0);
    for (let i = 0; i < basketProductList.length; i++) {
      props.setBasketTotalCost(
        (prev) =>
          prev + basketProductList[i].pdCost * basketProductList[i].count
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
            <button
              className="btn btn-xs btn-circle bg-outline-container"
              onClick={() => setProductCount(props.product.pdCode, "minus")}
            >
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
        <div className="mr-2">
          {props.product.count? Number(props.product.pdCost * props.product.count).toLocaleString(): props.product.pdCost}
          원
        </div>
        {props.type === "basket" ? null : (
          <div
            className={`text-sm ${
              props.product.pdCost > props.product.tagCost
                ? "text-myerror"
                : "text-myprimary"
            }`}
          >
            {props.product.pdCost > props.product.tagCost
              ? `▲${props.product.pdCost - props.product.tagCost}`
              : `▼${props.product.tagCost - props.product.pdCost}`}
          </div>
        )}
      </div>
    </div>
  );
};

export default ProductCard;
