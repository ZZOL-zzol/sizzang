import StampImg from './StampImg'

const StampCard = (props) => {

  console.log(props.stamp.mascotUrl)

  return (
    <div className="card w-[290px] h-[400px] bg-base-100 shadow-xl p-5 ml-10">
      <figure>
        <StampImg src={props.stamp.mascotUrl}></StampImg>
      </figure>
      <div className="card-body text-left">
        <h2 className="card-title">{props.stamp.mascotName}</h2>
        <div>{props.stamp.regionNameSecond}</div>
      </div>
    </div>
  );
};

export default StampCard;
