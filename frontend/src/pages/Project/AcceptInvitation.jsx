import React from "react"
import { Button } from "../../components/ui/button"
import { useDispatch } from "react-redux"
import { acceptInvitation } from "../../Redux/Project/Action"
import { useNavigate } from "react-router-dom"


export const AcceptInvitation = () => {
    const dispatch = useDispatch()
    const urlParams = new URLSearchParams(location.search);
    const token = urlParams.get("token")
    const navigate = useNavigate()
    const handleAcceptInvitation = ()=>{
        dispatch(acceptInvitation({token,navigate}))

    }
    return(
        <div className="h-[85vh] flex flex-col justify-center items-center">
            <h1 className="py-5 font-semibold text-xl">You are invited to the project </h1>
            <Button onClick={handleAcceptInvitation}>Accept Invitation</Button>

        </div>
    )
}

export default AcceptInvitation



