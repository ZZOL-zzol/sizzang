const Rating = (props) => {

  const onStarClick = (e) => {
    props.setReviewScore(e.target.value)
  }

  return (
    <div className="rating rating-lg rating-half">
      <input type="radio" name="rating-10" className="rating-hidden" onChange={onStarClick}
        value={0}/>
      <input
        type="radio"
        name="rating-10"
        className="bg-yellow-300 mask mask-star-2 mask-half-1"
        onChange={onStarClick}
        value={1}
        defaultChecked
      />
      <input
        type="radio"
        name="rating-10"
        className="bg-yellow-300 mask mask-star-2 mask-half-2"
        onChange={onStarClick}
        value={2}
      />
      <input
        type="radio"
        name="rating-10"
        className="bg-yellow-300 mask mask-star-2 mask-half-1"
        onChange={onStarClick}
        value={3}
      />
      <input
        type="radio"
        name="rating-10"
        className="bg-yellow-300 mask mask-star-2 mask-half-2"
        onChange={onStarClick}
        value={4}
      />
      <input
        type="radio"
        name="rating-10"
        className="bg-yellow-300 mask mask-star-2 mask-half-1"
        onChange={onStarClick}
        value={5}
      />
      <input
        type="radio"
        name="rating-10"
        className="bg-yellow-300 mask mask-star-2 mask-half-2"
        onChange={onStarClick}
        value={6}
      />
      <input
        type="radio"
        name="rating-10"
        className="bg-yellow-300 mask mask-star-2 mask-half-1"
        onChange={onStarClick}
        value={7}
      />
      <input
        type="radio"
        name="rating-10"
        className="bg-yellow-300 mask mask-star-2 mask-half-2"
        onChange={onStarClick}
        value={8}
      />
      <input
        type="radio"
        name="rating-10"
        className="bg-yellow-300 mask mask-star-2 mask-half-1"
        onChange={onStarClick}
        value={9}
      />
      <input
        type="radio"
        name="rating-10"
        className="bg-yellow-300 mask mask-star-2 mask-half-2"
        onChange={onStarClick}
        value={10}
      />
    </div>
  );
};

export default Rating;
