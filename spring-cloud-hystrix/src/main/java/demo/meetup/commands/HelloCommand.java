/*
 * Copyright (C) 2015 Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo.meetup.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HelloCommand extends HystrixCommand<String> {


    protected HelloCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("HelloGroup"));
    }

    @Override
    protected String run() throws Exception {
        return "hello from " + InetAddress.getLocalHost().getHostName();
    }


    @Override
    protected String getFallback() {
        try {
            return "Hello Failure " + InetAddress.getLocalHost().getHostName() + "!";
        } catch (UnknownHostException e) {
            return "Hello world";
        }
    }
}
