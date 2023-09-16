const StampImg = (props) => {
  console.log(props.src)
  return (
    <div className="avatar">
      <div className="w-60 rounded">
        <img src={props.src} alt="stamp"/>
      </div>
    </div>
  );
};

export default StampImg;