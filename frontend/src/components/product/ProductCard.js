const ProductCard = (props) => {
  return (
    <div class="card card-side bg-base-100 rounded-none border-b-2">
      <div class="card-body p-3 justify-between">
        <div className="gap-0 flex justify-between">
          <div class="card-title text-base">{props.product}</div>
          <span className="text-sm text-left">{props.price}</span>
        </div>

        <div className='text-right text-sm text-myerror'>
            ▲1000
        </div>
      </div>
    </div>
  );    
};

export default ProductCard;
