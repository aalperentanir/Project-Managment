import React, { useEffect } from "react"
import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
  } from "@/components/ui/card"
  import { Button } from "@/components/ui/button"

import { CheckCircledIcon } from "@radix-ui/react-icons"
import { useNavigate } from "react-router-dom"
import { useDispatch, useSelector } from "react-redux"
import { getUserSubscription, upgradeSubscription } from "../../Redux/Subscription/Action"
  

export const UpgradeSuccess = () => {
    const navigate = useNavigate()
    const {subscription} = useSelector(store => store);
    const dispatch = useDispatch()

    const queryParams = new URLSearchParams(location.search)

    const paymentId = queryParams.get("payment_id");
    const subType = queryParams.get("subType");

    useEffect(()=>{
        dispatch(upgradeSubscription({subType}))
        dispatch(getUserSubscription())
    })
    return(
        <div className="flex justify-center">

            <Card className="mt-20 p-5 space-y-5 flex flex-col items-center">
                <div className="flex items-center gap-4">
                    <CheckCircledIcon className="h-9 w-9 text-green-500"/>
                    <p className="text-xl">Subscription upgraded successfully</p>

                </div>

                <div className="space-y-3">
                    <p className="text-green-500">Start date: {subscription.usersSubscription?.startedDate}</p>
                    <p className="text-red-500">End date: {subscription.usersSubscription?.endedDate}</p>
                    <p>Sub Type: {subscription.usersSubscription?.subType}</p>

                </div>

                <Button onClick={()=>navigate("/")}>Go to Home</Button>

            </Card>

        </div>
    )
}

export default UpgradeSuccess

