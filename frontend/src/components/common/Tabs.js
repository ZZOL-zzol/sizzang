import { useState } from "react";

const Tabs = (props) => {
  const[currentInfoTab, setCurrentInfoTab] = useState(0)

  return (
    <div className="tabs">
      <div className={currentInfoTab ===0?"tab tab-bordered flex flex-grow text-myprimary border-b-2 border-myprimary":"tab tab-bordered flex flex-grow"} onClick={()=>setCurrentInfoTab(0)}>{props.type === 'market'? '점포 목록' : '상품 목록'}</div>
      <div className={currentInfoTab ===1?"tab tab-bordered flex flex-grow text-myprimary border-b-2 border-myprimary":"tab tab-bordered flex flex-grow"} onClick={()=>setCurrentInfoTab(1)}>리뷰</div>
    </div>
  );
};

export default Tabs;