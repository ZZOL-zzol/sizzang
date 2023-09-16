import { useState } from "react";

const Tabs = (props) => {
  const [currentInfoTab, setCurrentInfoTab] = useState(0);
  // console.log(props.onTabClick)
  const setTabInfo = (value) => {
    setCurrentInfoTab(value);
    console.log(value)
    if (props.setCurrentCategory) {
      props.setCurrentCategory("all");
    }
    if (value === 0) {
      props.onTabClick(0);
    } else {
      props.onTabClick(1);
    }
  };

  return (
    <div className="tabs">
      <div
        className={
          currentInfoTab === 0
            ? "tab tab-bordered flex flex-grow text-myprimary border-b-2 border-myprimary"
            : "tab tab-bordered flex flex-grow"
        }
        onClick={() => setTabInfo(0)}
      >
        {props.tab1}
      </div>
      <div
        className={
          currentInfoTab === 1
            ? "tab tab-bordered flex flex-grow text-myprimary border-b-2 border-myprimary"
            : "tab tab-bordered flex flex-grow"
        }
        onClick={() => setTabInfo(1)}
      >
        {props.tab2}
      </div>
    </div>
  );
};

export default Tabs;
