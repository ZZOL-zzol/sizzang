const Card = () => {
  return (
    <div class="card card-side bg-base-100 shadow-xl">
      <figure className="m-3 rounded-lg w-52">
        <img
            className="w-full h-full"
          src="./chacha2.jpg"
          alt="Movie"
        />
      </figure>
      <div class="card-body">
        <h2 class="card-title">가게 이름</h2>
        <p>설명</p>
        <div class="card-actions justify-end">
          <button class="btn btn-primary">Watch</button>
        </div>
      </div>
    </div>
  );
};

export default Card;
