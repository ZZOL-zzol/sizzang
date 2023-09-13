import Header from "../components/common/Header";
import Navbar from "../components/common/Navbar";
import HistoryCard from "../components/history/HistoryCard";

const historyList = [
    {hsName : '반반윙x1 외 1건', hsDate : '2023.09.10', hsCost : '40,000', reviewed:false},
    {hsName : '반반윙x1 외 1건', hsDate : '2023.09.10', hsCost : '40,000', reviewed:true},
]

const HistoryPage = () => {
    return(
        <div className="w-full bg-background-fill">
            <Header title='내 소비내역' backButton route='/profile'/>
            <div className="flex flex-col w-full mt-5 gap-2">
                {historyList.map(history=> <HistoryCard history={history}/>)}
                
            </div>
            <Navbar/>
        </div>
    )
}

export default HistoryPage;