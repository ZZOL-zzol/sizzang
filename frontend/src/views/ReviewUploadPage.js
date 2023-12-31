import { useState } from "react";
import { useLocation } from 'react-router-dom'; 
import ReviewCard from '../components/review/ReviewCard'
import ReviewForm from '../components/review/ReviewForm'
import Header from "../components/common/Header";




const ReviewUploadPage =()=> {
    const location = useLocation();

const [history, setHistory] = useState(
  location.state?.history
);
    return(
        <div className="w-full">
        <Header title='리뷰 쓰기' backButton/>
        <div className="h-full py-16">
        <ReviewCard history={history} type='writing'/>
        <ReviewForm history={history} accountNumber={location.state.accountNumber}/>
        </div>
        </div>
    )
}

export default ReviewUploadPage;