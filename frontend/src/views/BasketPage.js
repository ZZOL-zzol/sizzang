import ProductCard from "../components/store/ProductCard";
import Header from "../components/common/Header";
import { useEffect, useState } from "react";
import AccountCard from "../components/basket/AccountCard";

// const basket =
//   {
//     stName: "네네치킨",
//     stAddress: "관악구 봉천로 466",
//     stPhone: "010-6664-9510",
//     productList: [
//       {
//         pdCode: 1,
//         pcCode: 1,
//         stCode: "",

//         mkCode: "",
//         scCode: "",
//         pdName: "옛날통닭",
//         pdCost: 50000,
//         pdIntro: "파닭파닭",
//         count: 1,
//       },
//       {
//         pdCode: 1,
//         pcCode: 1,
//         stCode: "",

//         mkCode: "",
//         scCode: "",
//         pdName: "옛날통닭",
//         pdCost: 50000,
//         pdIntro: "파닭파닭",
//         count: 1,
//       },
//       {
//         pdCode: 1,
//         pcCode: 1,
//         stCode: "",

//         mkCode: "",
//         scCode: "",
//         pdName: "옛날통닭",
//         pdCost: 50000,
//         pdIntro: "파닭파닭",
//         count: 1,
//       },
//     ],
//   };

const accountList = [
  {
    accountCode: 1,
    accountHolder: "차차아버님",
    accountNumber: "123-456-789",
    accountName: "차차야그만방해해계좌",
    accountBalance: 200000,
  },
  {
    accountCode: 1,
    accountHolder: "차차아버님",
    accountNumber: "123-456-789",
    accountName: "차차야그만방해해계좌",
    accountBalance: 200000,
  },
];

const BasketPage = () => {
  const [showAccount, setShowAccount] = useState(false);
  const onPayButtonClick = () => {
    if (!showAccount) {
      setShowAccount(true);
    } else {
      //결제를 어떤식으로 할건지...
      //여기에 들어가야한다요
      //신한 이체 api 사용 예정
    }
  };
  const [basket, setBasket] = useState({});
  const [basketTotalCost, setBasketTotalCost] = useState(0);

  useEffect(() => {
    const basket = JSON.parse(window.localStorage.getItem("basket"));
    if (basket) setBasket(basket);

    for (let i = 0; i < basket.productList.length; i++) {
      setBasketTotalCost(
        (prev) =>
          prev + basket.productList[i].pdCost * basket.productList[i].count
      );
    }
  }, []);

  return (
    <div className="w-full h-full bg-background-fill">
      <Header
        title="장바구니"
        backButton
        route="javascript:window.history.back()"
      />
      <div className="flex flex-col w-full">
        {basket.productList ? (
          <div className="w-full">
            <div className="w-full p-5 bg-white mb-3 flex flex-col items-start">
              <div className="font-semibold">{basket.stName}</div>
              <div className="text-sm">{basket.stAddress}</div>
              <div className="text-sm">{basket.stPhone}</div>
            </div>

            {basket.productList.map((product, index) => (
              <ProductCard
                product={product}
                basket={basket}
                type="basket"
                setBasket={setBasket}
                index={index}
              />
            ))}
          </div>
        ) : (
          <div className="w-full p-5 bg-white mb-3 flex flex-col items-center gap-2">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="3em"
              viewBox="-20 -20 600 550"
              className="fill-white stroke-[20px] stroke-surface"
            >
              <path d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z" />
            </svg>
            장바구니가 비어있습니다.
          </div>
        )}
        {basket.productList ? (
          <div className="flex flex-col items-end justify-center bg-white h-[50px] px-5 text-lg">
            {basketTotalCost.toLocaleString()}원
          </div>
        ) : null}
      </div>

      <div className="fixed w-full flex flex-col bg-white bottom-0 items-center justify-center px-5 py-7 gap-2">
        {showAccount
          ? accountList.map((account) => (
              <AccountCard
                account={account}
                setShowAccount={setShowAccount}
                type="pay"
              />
            ))
          : null}

        <button
          className="btn h-[40px] w-full bg-myprimary text-xl text-white"
          onClick={() => onPayButtonClick()}
        >
          {showAccount ? "결제하기" : "바로 결제"}
        </button>
      </div>
    </div>
  );
};

export default BasketPage;
