import EditProductCard from "./EditProductCard";
import ProductAddForm from "./ProductAddForm";
import axios from "axios";
import { API_URL } from "../../../lib/constants";
import { useEffect, useState } from "react";

const ProductEditCard = (props) => {
  const [productList, setProductList] = useState([]);
  const [openAddForm, setOpenAddForm] = useState(false);

  useEffect(() => {
    axios
      .get(`${API_URL}/product/1`)
      .then((res) => setProductList(res.data.data));
  }, []);

  return (
    <div>
      <div className="flex flex-col items-center gap-3">
        <div className="flex w-full justify-between">
          <button
            className="btn btn-ghost normal-case text-xl font-environmentR"
            onClick={() => props.setOpenProductEdit(false)}
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="1em"
              viewBox="0 0 384 512"
            >
              <path d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z" />
            </svg>
          </button>
          <div className="flex items-center text-xl font-bold">상품 관리</div>
        </div>
        {productList.map((product) => (
          <EditProductCard product={product} />
        ))}
        {openAddForm ? (
          <ProductAddForm
            setOpenAddForm={setOpenAddForm}
            setProductList={setProductList}
          />
        ) : (
          <div
            className="w-full h-[60px] bg-myprimary rounded-lg flex items-center justify-center"
            onClick={() => setOpenAddForm(true)}
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="2em"
              viewBox="0 0 448 512"
              className="fill-white"
            >
              <path d="M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32V224H48c-17.7 0-32 14.3-32 32s14.3 32 32 32H192V432c0 17.7 14.3 32 32 32s32-14.3 32-32V288H400c17.7 0 32-14.3 32-32s-14.3-32-32-32H256V80z" />
            </svg>
          </div>
        )}
      </div>
    </div>
  );
};

export default ProductEditCard;
