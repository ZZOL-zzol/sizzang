import StampImg from './StampImg'

const StampCard = () => {
  return (
    <div className="card w-96 h-[400px] bg-base-100 shadow-xl p-5">
      <figure>
        <StampImg></StampImg>
      </figure>
      <div className="card-body text-left">
        <h2 className="card-title">마스코트 이름</h2>
        <div>서울시 마스코트</div>
      </div>
    </div>
  );
};

export default StampCard;
