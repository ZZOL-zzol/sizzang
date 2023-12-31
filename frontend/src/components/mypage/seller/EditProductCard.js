const EditProductCard = (props) => {
  console.log(props.product);
  return (
    <div className="w-full bg-white p-5 border-b-2 border-outline-container">
      <div className="flex justify-between items-center">
        <div className="flex flex-col items-start">
          <div className="flex gap-2 items-end">
            <div className="">{props.product.pdName}</div>
            <div className="text-outline text-sm">{props.product.tagName}</div>
          </div>
          <div>{Number(props.product.pdCost).toLocaleString()}원</div>
        </div>
        <div className="flex gap-5">
          <div className="text-outline text-right text-sm w-[100px]">
            {props.product.pdIntro}
          </div>
          <button>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="1.5em"
              viewBox="0 0 448 512"
            >
              <path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z" />
            </svg>
          </button>
        </div>
      </div>
    </div>
  );
};

export default EditProductCard;
