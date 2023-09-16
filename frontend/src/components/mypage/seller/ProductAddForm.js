import { useState } from "react";
import EditProductInput from "./EditProductInput";
import axios from "axios";
import { API_URL } from "../../../lib/constants";

const ProductAddForm = (props) => {
  const [product, setProduct] = useState({});

  const onNameChange = (e) => {
    setProduct((prev) => {
      let before = { ...prev };
      before.pdName = e.target.value;
      return before;
    });
  };

  const onCostChange = (e) => {
    setProduct((prev) => {
      let before = { ...prev };
      before.pdCost = e.target.value;
      return before;
    });
  };

  const onIntroChange = (e) => {
    setProduct((prev) => {
      let before = { ...prev };
      before.pdIntro = e.target.value;
      return before;
    });
  };

  const onAddButtonClick = () => {
    const newProduct = {
      tagCode: 90,
      stCode: props.store.stCode,
      pdCost: product.pdCost,
      pdName: product.pdName,
      pdIntro: product.pdIntro,
    };
    const formData = new FormData();
    props.setProductList((prev) => [...prev, newProduct]);
    formData.append(
      "registInfo",
      new Blob([JSON.stringify(newProduct)], { type: "application/json" })
    );
    axios
      .post(`${API_URL}/product`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((res) => {
        console.log(res);
        props.setOpenAddForm(false);
      })
      .catch((err) => console.log(err));
  };

  return (
    <div className="flex justify-between w-full px-5">
      <div className="flex flex-col gap-2">
        <EditProductInput
          value={product.pdName}
          onInputChange={onNameChange}
          placeholder="제품명"
        />
        <EditProductInput
          value={product.pdCost}
          onInputChange={onCostChange}
          placeholder="가격"
        />
        <EditProductInput
          value={product.pdIntro}
          onInputChange={onIntroChange}
          placeholder="제품 설명"
        />
      </div>
      <div className="flex gap-3">
        <button onClick={onAddButtonClick}>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="2em"
            viewBox="0 0 448 512"
            className="fill-myprimary"
          >
            <path d="M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32V224H48c-17.7 0-32 14.3-32 32s14.3 32 32 32H192V432c0 17.7 14.3 32 32 32s32-14.3 32-32V288H400c17.7 0 32-14.3 32-32s-14.3-32-32-32H256V80z" />
          </svg>
        </button>
        <button onClick={() => props.setOpenAddForm(false)}>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="2em"
            viewBox="0 0 384 512"
          >
            <path d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z" />
          </svg>
        </button>
      </div>
    </div>
  );
};

export default ProductAddForm;
